package com.zuhaib.examPortalUserService.controller

import com.zuhaib.examPortalUserService.service.AwsStorageService
import com.zuhaib.examPortalUserService.service.AwsRekognitionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.ByteBuffer


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/file")
class AwsStorageController {

    @Autowired
    private var rekognitionService : AwsRekognitionService?=null

    @Autowired
    private var service : AwsStorageService? = null

    @PostMapping("/upload")
    fun uploadFile(@RequestParam(value = "file") file : MultipartFile): ResponseEntity<*>? {
       var mss = service!!.uploadFile(file)
        return ResponseEntity.ok(mss)
    }

    @GetMapping("/download")
    fun downloadFile(@RequestBody fileName: String?): ResponseEntity<String?>? {
        val data: ByteBuffer? = service!!.downloadFile(fileName)
        println(data)
        return ResponseEntity.ok("SuccesFullydone")
    }

    @PostMapping("/verifyimage")
    fun verifyImage(@RequestPart file: MultipartFile): ResponseEntity<*>? {
        return ResponseEntity.ok(rekognitionService?.imageVerify(file))
    }


    @PostMapping("/deleteimage")
    fun deleteFile(@RequestBody username : String?): ResponseEntity<*>? {
       var mms : String? = service?.deleteFile(username!!)
        return ResponseEntity.ok(mms)
    }
}
