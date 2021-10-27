package com.zuhaib.examPortalUserService.service

import com.amazonaws.services.rekognition.AmazonRekognition
import com.amazonaws.services.rekognition.model.*
import com.amazonaws.util.IOUtils
import com.zuhaib.examPortalUserService.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.nio.ByteBuffer

@Service
class AwsRekognitionService {

    @Autowired
    val amazonRekognition: AmazonRekognition? =null

    @Autowired
    val userService: UserService?=null

    @Autowired
    val storageService: AwsStorageService?=null


    fun imageVerify(file: MultipartFile) :String{
        var confidence :Float =0F
        var message :String =""
        var sourceImageBytes: ByteBuffer? = null
        try {
            val fileObj = convertMultiPartFileToFile(file)
            var inputStream : InputStream = FileInputStream(fileObj)
            sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream))
        }catch (e:Exception){
            println("Couldn't Load Source Image $e")
        }
        var targetImageBytes :ByteBuffer?= null
        val user : User = SecurityContextHolder.getContext().authentication.principal as User
        var filename :String = user.getProfileFileName()
        try {
            targetImageBytes = storageService?.downloadFile(filename)
        }catch (e:Exception){
            println("Couldn't DownloadImage from S3 $e")
        }

        var source :Image = Image().withBytes(sourceImageBytes)
        var target:Image = Image().withBytes(targetImageBytes)

        val request = CompareFacesRequest()
            .withSourceImage(source)
            .withTargetImage(target)
            .withSimilarityThreshold(0F)
        try {
            // Call operation
            val compareFacesResult: CompareFacesResult? = amazonRekognition?.compareFaces(request)

            var comparedFace : List<CompareFacesMatch>? = compareFacesResult!!.faceMatches
            if (comparedFace != null) {
                for(compared in comparedFace){
                    confidence = compared.similarity
                    println(confidence)
                }
            }



            var uncompared :List<ComparedFace> = compareFacesResult!!.unmatchedFaces;
            println("There was ${uncompared!!.size} face(s) that did not match");

        }catch (e:Exception){
            println("Error $e")
        }
        message = if(confidence > 70F){
            "SUCCESS"
        }else{
            "Fail"
        }

        return message

    }


    private fun convertMultiPartFileToFile(file: MultipartFile): File{
        val convertedFile = File(file.originalFilename)
        try {
            FileOutputStream(convertedFile).use { fos -> fos.write(file.bytes) }
        } catch (e: IOException) {

        }
        return convertedFile
    }
}