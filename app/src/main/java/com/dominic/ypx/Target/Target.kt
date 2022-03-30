package com.dominic.ypx.Target

import android.net.Uri
import java.io.Serializable

class Target:Serializable {
    var id:Int? = null
    var target_name:String? = null
    var target_info:String? = null
    var target_image:Uri? = null
    var target_type:String? = null
    var target_isliked:String? = null

    constructor(
        id: Int?,
        target_name: String?,
        target_info: String?,
        target_image: Uri?,
        target_type: String?,
        target_isliked: String?
    ) {
        this.id = id
        this.target_name = target_name
        this.target_info = target_info
        this.target_image = target_image
        this.target_type = target_type
        this.target_isliked = target_isliked
    }

    constructor(
        target_name: String?,
        target_info: String?,
        target_image: Uri?,
        target_type: String?,
        target_isliked: String?
    ) {
        this.target_name = target_name
        this.target_info = target_info
        this.target_image = target_image
        this.target_type = target_type
        this.target_isliked = target_isliked
    }
    constructor(target_name: String?,target_image: Uri?,target_isliked: String?){
        this.target_name = target_name
        this.target_image = target_image
        this.target_isliked = target_isliked
    }

}