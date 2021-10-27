package com.zuhaib.examPortalUserService.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.zuhaib.examPortalUserService.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer



@Service
class AwsStorageService {
    @Value("\${amazonProperties.bucketName}")
    private val bucketName: String? = null

    @Value("\${amazonProperties.endpointUrl}")
    private val endpointUrl: String? = null

    @Autowired
    private val s3Client: AmazonS3? = null

    @Autowired
    private val userService : UserService?= null

    fun uploadFile(file: MultipartFile): String {
        val fileObj = convertMultiPartFileToFile(file)
        val fileName = System.currentTimeMillis().toString() + "_" + file.originalFilename
        val fileUrl = "$endpointUrl/$bucketName/$fileName";
        val ree = s3Client!!.putObject(PutObjectRequest(bucketName, fileName, fileObj))
        fileObj!!.delete()
        val user : User = SecurityContextHolder.getContext().authentication.principal as User
        user.setProfileLocation(fileUrl)
        user.setProfileFileName(fileName)
        userService?.updateUser(user)
        return fileUrl
    }

    fun downloadFile(fileName: String?): ByteBuffer? {
        val s3Object: S3Object = s3Client!!.getObject(bucketName, fileName)
        val inputStream: S3ObjectInputStream = s3Object.objectContent
        try {
            val content: ByteBuffer = ByteBuffer.wrap(IOUtils.toByteArray(inputStream))
            println(content)
            return content
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun deleteFile(username: String): String? {
        val user = userService?.getUser(username)
        var filename = user?.getProfileFileName()

        s3Client!!.deleteObject(bucketName, filename)

        user!!.setProfileFileName(" ")
        user.setProfileLocation(" ")
        userService?.updateUser(user)
        return "SuccessFully Deleted $filename"
    }

    private fun convertMultiPartFileToFile(file: MultipartFile): File? {
        val convertedFile = File(file.originalFilename)
        try {
            FileOutputStream(convertedFile).use { fos -> fos.write(file.bytes) }
        } catch (e: IOException) {

        }
        return convertedFile
    }
}