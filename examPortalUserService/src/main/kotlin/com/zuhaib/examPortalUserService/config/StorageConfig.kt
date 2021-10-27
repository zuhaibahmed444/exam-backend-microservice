package com.zuhaib.examPortalUserService.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.rekognition.AmazonRekognition
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration





@Configuration
class StorageConfig {

    @Value("\${amazonProperties.accessKey}")
    private val accessKey: String? = null

    @Value("\${amazonProperties.secretKey}")
    private val accessSecret: String? = null

    @Value("\${amazonProperties.region}")
    private val region: String? = null

    @Bean
    fun s3client(): AmazonS3{
        val credentials = BasicAWSCredentials(accessKey,accessSecret)
        return AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .withRegion(region).build()
    }

    @Bean
    fun amazonRekognition(): AmazonRekognition? {
        val credentials = BasicAWSCredentials(accessKey,accessSecret)
        return AmazonRekognitionClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .build()
    }


}