package com.example.receiptpocket.pocket.win.Interactors

import android.util.Log
import com.example.receiptpocket.App
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.pocket.win.Listeners.OnWinReceiptGetListener
import com.example.receiptpocket.pocket.win.ReceiptWinItem
import java.sql.SQLException

class WinInteractorImpl: WinInteractor {
    override fun checkWin(
        year: String, month: String,
        first: String, second: String, third: String, four: String, fif: String,
        listener: OnWinReceiptGetListener
    ) {
        val year_ = year.trim().toInt()
        var month1 = 0

        // for find month1
        val firstNum = month.trim().get(0)
        if(firstNum != '1') {
            month1 = firstNum.toString().toInt()
        }
        else {
            val secondNum = month.trim().get(1)
            if(secondNum == '-') { month1 = firstNum.toString().toInt() }
            else { month1 = (month.trim().substring(0,2).toInt()) }
        }

        val month2 = month1 + 1
        val first_ = first.trim()
        val second_ = second.trim()
        val third_ = third.trim()
        val four_ = four.trim()
        val fif_ = fif.trim()


        Thread{
            try {
                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()

                var winItems = mutableListOf<ReceiptWinItem>()

                // 特別獎
                val winFirstItems = receiptDao.getAllMatchItems(year_, month1, month2, first_)
                winItems += packReceiptWinItems(winFirstItems, "$10,000,000")

                // 特獎
                val winSecondItems = receiptDao.getAllMatchItems(year_, month1, month2, second_)
                winItems += packReceiptWinItems(winSecondItems, "$2,000,000")

                // 頭獎-1
                val winThirdItems = receiptDao.getThreeMatchItems(year_, month1, month2, third_.substring(5, 8))
                winItems += checkThird2FifWinItem(winThirdItems, third_)

                // 頭獎-2
                val winFourItems = receiptDao.getThreeMatchItems(year_, month1, month2, four_.substring(5, 8))
                winItems += checkThird2FifWinItem(winFourItems, four_)

                // 頭獎-3
                val winFifItems = receiptDao.getThreeMatchItems(year_, month1, month2, fif_.substring(5, 8))
                winItems += checkThird2FifWinItem(winFifItems, fif_)

                listener.getWinItemSuccess(winItems)

            } catch (e: SQLException){
                e.printStackTrace()
            }

        }.start()

    }

    override fun loadOneItem(
        id: String,
        code_1: String,
        code_2: String,
        prize: String,
        listener: OnWinReceiptGetListener
    ) {
        Thread{
            try {
                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
                val receiptItem = receiptDao.getOneItem(id, code_1, code_2)

                listener.getOneItemSuccess(receiptItem, prize)

            } catch (e: SQLException){
                e.printStackTrace()
            }

        }.start()

    }

    private fun packReceiptWinItems(items: List<Receipt>, priceStr: String): List<ReceiptWinItem>{
        val returnItems = mutableListOf<ReceiptWinItem>()
        for(item in items){
            returnItems.add(packReceiptWinOneItem(item, priceStr))
        }
        return returnItems
    }

    private fun packReceiptWinOneItem(item: Receipt, prizeStr: String): ReceiptWinItem{
        val fDate = item.month.toString() + '/' + item.day.toString()
        val fStore = item?.store ?: ""
        val fCode = item.code_1 + '-' + item.code_2
        val fPrice = item.price?.toString() ?: ""

        return ReceiptWinItem(fDate, fStore, fCode, fPrice, prizeStr)
    }

    private fun checkThird2FifWinItem(items: List<Receipt>, code: String): List<ReceiptWinItem>{
        Log.v("DB", "enter check third !!")

        val returnItems = mutableListOf<ReceiptWinItem>()
        for(item in items){
            Log.v("DB", "enter check third while!!")

            if(item.code_2 == code){ // 8數全中
                returnItems.add(packReceiptWinOneItem(item, "$200,000"))
            }
            else if(item.code_2.substring(1,8) == code.substring(1,8)){ //末7碼全中
                returnItems.add(packReceiptWinOneItem(item, "$40,000"))
            }
            else if(item.code_2.substring(2,8) == code.substring(2,8)){ //末6碼全中
                returnItems.add(packReceiptWinOneItem(item, "$10,000"))
            }
            else if(item.code_2.substring(3,8) == code.substring(3,8)){ //末5碼全中
                returnItems.add(packReceiptWinOneItem(item, "$4,000"))
            }
            else if(item.code_2.substring(4,8) == code.substring(4,8)){ //末4碼全中
                returnItems.add(packReceiptWinOneItem(item, "$1,000"))
            }
            else{ //末3碼全中
                returnItems.add(packReceiptWinOneItem(item, "$200"))
            }
        }

        return returnItems
    }
}