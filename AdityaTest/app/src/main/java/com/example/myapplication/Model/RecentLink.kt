package com.example.myapplication.Model

data class RecentLink(
    val app: String,
    val created_at: String,
    val domain_id: String,
    val original_image: String,
    val smart_link: String,
    val thumbnail: Any,
    val times_ago: String,
    val title: String,
    val total_clicks: String,
    val url_id: String,
    val url_prefix: Any,
    val url_suffix: String,
    val web_link: String
):java.io.Serializable