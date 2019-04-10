package com.example.daggerandroidcomponentskotlin.db;


public class MyPojo {
    var per_page: String? = null

    var total: String? = null

    var data: Array<Data>? = null

    var page: String? = null

    var total_pages: String? = null

    override fun toString(): String {
        return "ClassPojo [per_page = $per_page, total = $total, data = $data, page = $page, total_pages = $total_pages]"
    }
}
