package com.zuhaib.examPortalUserService.model

class JwtResponse {
    var token: String? = null

    constructor(token: String?) {
        this.token = token
    }

    constructor() {}
}