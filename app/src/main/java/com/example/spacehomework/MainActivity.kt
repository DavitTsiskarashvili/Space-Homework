package com.example.spacehomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.ThemeUtils
import com.example.spacehomework.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}

fun main() {
    val bank = Bank()
    val (davit, temur, emzar) = users()

    bank.moneyTransferFee(davit)
    bank.moneyTransferFee(temur)
    bank.moneyTransferFee(emzar)

    bank.chargeMonthlyFee(listOf(davit, temur, emzar))

    println("Davit's balance after transaction is ${davit.balance}")
    println("Temur's balance after transaction is ${temur.balance}")
    println("Emzar's balance after transaction is ${emzar.balance}")

    println("Bank has made income with commissions in total amount of ${bank.commission}")
}

fun users(): Triple<Citizen, Entrepreneur, Politician> {
    val davit = Citizen(
        "Davit",
        10000f,
        100000f,
        "GEO")

    val temur = Entrepreneur(
        "Temur Tchkonia",
        2000f,
        500f,
        "beverages"
    )

    val emzar = Politician(
        "Emzar Kvitsiani",
        800f,
        73f,
        "Ras Davachire Amistanas"
    )
    return Triple(davit, temur, emzar)
}