package com.example.receiptpocket

import android.util.Log
import java.sql.DriverManager
import java.sql.SQLException

class MysqlCon {

    private val mysql_ip: String = "db4free.net" //"127.0.0.1"
    private val mysql_port: Int = 3306
    private val db_name: String = "receipt_db"
    private val url: String = "jdbc:mysql://${mysql_ip}:${mysql_port}/${db_name}" //?useSSL=false&allowPublicKeyRetrieval=true"
    private val db_user: String = "receipt_pocket" //"root"
    private val db_password: String = "test.mysql" //"root123"

    fun run(){
        // 加載driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            Log.v("DB", "Load driver successes")
        } catch (e:ClassNotFoundException) {
            Log.e("DB", "Load driver fails")

        }

        // 連線MySQL資料庫
        try {
            val con = DriverManager.getConnection(url, db_user, db_password)
            Log.v("DB", "connection successes")
        } catch (e:SQLException){
            Log.e("DB", "connection fails")
            e.printStackTrace()
            e.message
        }
    }

    fun getAccount(id_: String): MutableList<String>{
        val nplist = mutableListOf<String>()
        try {
            val con = DriverManager.getConnection(url, db_user, db_password)
            val sql = "SELECT * FROM Account WHERE id='${id_}'"
            val st = con.createStatement()
            val rs = st.executeQuery(sql)

            while (rs.next()){
                val id = rs.getString("id")
                val name = rs.getString("name")
                val password = rs.getString("password")

                nplist.add(0, id)
                nplist.add(1, name)
                nplist.add(2, password)
            }
            st.close()

        } catch (e: SQLException){
            e.printStackTrace()
        }

        return nplist
    }


}