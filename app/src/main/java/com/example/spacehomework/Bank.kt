package com.example.spacehomework

class Bank {
    var commission: Float = 0.0f


    fun moneyTransferFee(user: Any) {
        when (user) {
            is Entrepreneur -> {
                val fee = user.income * 0.1f
                user.balance -= fee
                commission += fee
            }
            is Politician -> {
                val fee = user.income * 0.2f
                user.balance -= fee
                commission += fee
            }
            is Citizen -> {
                // Citizens do not pay fee for wire transactions
            }
        }
    }

    fun chargeMonthlyFee(users: List<Any>) {
        users.forEach { user ->
            when (user) {
                is Citizen -> {
                    var fee = user.income * 0.02f
                    user.balance -= fee
                    commission += fee
                }
                is Entrepreneur -> {
                    val fee = user.income * 0.04f
                    user.balance -= fee
                    commission += fee
                }
                is Politician -> {
                    val fee = user.income * 0.06f
                    user.balance -= fee
                    commission += fee
                }
            }
        }
    }

}