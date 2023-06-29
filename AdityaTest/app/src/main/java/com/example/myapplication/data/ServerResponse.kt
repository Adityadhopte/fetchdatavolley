package com.example.myapplication.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//data class ServerResponse(
//  val Code:Int,
//  val Success:Boolean,
//  val Message:String,
//)


@JsonClass(generateAdapter = true)
data class ServerResponse(


    @Json(name = "status") var status: Boolean? = null,
    @Json(name="statusCode") var statusCode: String? = null,
    @Json(name="message") var message: String? = null,
    @Json(name="support_whatsapp_number") var supportWhatsappNumber: String? = null,
    @Json(name="extra_income") var extraIncome: String? = null,
    @Json(name="total_links") var totalLinks: String? = null,
    @Json(name="total_clicks") var totalClicks: String? = null,
    @Json(name="today_clicks") var todayClicks: String? = null,
    @Json(name="top_source") var topSource: String? = null,
    @Json(name="top_location") var topLocation: String? = null,
    @Json(name="startTime") var startTime: String? = null,
    @Json(name="links_created_today") var linksCreatedToday: String? = null,
    @Json(name="applied_campaign") var appliedCampaign: Int? = null,
    @Json(name="data") var data: Data? = Data()

) : java.io.Serializable