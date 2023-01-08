package com.example.receiptpocket

import android.util.Log
import java.sql.DriverManager
import java.sql.SQLException

class MysqlCon {

    private val mysql_ip: String = "db4free.net" //"192.168.2.26"
    private val mysql_port: Int = 3306
    private val db_name: String = "receipt_db"
    private val url: String = "jdbc:mysql://${mysql_ip}:${mysql_port}/${db_name}" //?useSSL=false&allowPublicKeyRetrieval=true"
    private val db_user: String =  "receipt_pocket"  //"root"
    private val db_password: String = "test.mysql"  //"root123"

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


    fun insertAccount(id: String, name: String, password: String){
        try {
            val con = DriverManager.getConnection(url, db_user, db_password)
            val sql = "INSERT INTO Account(id, name, password) VALUES('${id}', '${name}', '${password}')"
            val st = con.createStatement()
            st.executeUpdate(sql)
            st.close()

        } catch (e: SQLException){
            e.printStackTrace()
        }
    }

    fun getReceipts(id_: String): MutableList<ReceiptModel>{
        val receiptList = mutableListOf<ReceiptModel>()
        try {
            val con = DriverManager.getConnection(url, db_user, db_password)
            val sql = "SELECT * FROM Receipt WHERE id='${id_}'"
            val st = con.createStatement()
            val rs = st.executeQuery(sql)

            while (rs.next()){
                val sid = rs.getString("sid")
                val store = rs.getString("store")
                val year = rs.getInt("year")
                val month = rs.getInt("month")
                val day = rs.getInt("day")
                val code_1 = rs.getString("code_1")
                val code_2 = rs.getString("code_2")
                val price = rs.getInt("price")
                val describes = rs.getString("describes")

                receiptList.add(ReceiptModel(sid=sid, store=store, year=year, month=month, day=day,
                    code_1=code_1, code_2=code_2, price=price, describes=describes))
            }
            st.close()
        } catch (e: SQLException){
            e.printStackTrace()
        }

        return receiptList
    }

    fun insertReceipts(receiptsList: MutableList<ReceiptModel>){
        try {
            val sql = ""
            for(item in receiptsList){
                sql.plus("INSERT INTO Receipt(sid, store, year, month, day, code_1, code_2, price, describes) " +
                        "VALUES('${item.sid}', '${item.store}', ${item.year}, ${item.month}, ${item.day}, " +
                        "'${item.code_1}', '${item.code_2}', ${item.price}, '${item.describes}'); ")
            }

            val con = DriverManager.getConnection(url, db_user, db_password)
            val st = con.createStatement()
            st.executeUpdate(sql)
            st.close()

        } catch (e: SQLException){
            e.printStackTrace()
        }
    }

    fun deleteReceiptsById(id_: String){
        try {
            val con = DriverManager.getConnection(url, db_user, db_password)
            val sql = "DELETE FROM Receipt WHERE id= '${id_}'"
            val st = con.createStatement()
            st.executeUpdate(sql)

        } catch (e: SQLException){
            e.printStackTrace()
        }
    }

}