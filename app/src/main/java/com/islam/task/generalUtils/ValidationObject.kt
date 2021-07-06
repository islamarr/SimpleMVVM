package com.islam.task.generalUtils

import android.os.Bundle
import com.islam.task.R
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.regex.Pattern


object ValidationObject {

    private const val fullNameRegex = "[a-zA-Zء-ي ]+"
    private const val passwordRegex =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,;:])[A-Za-z\\d@$!%*?&.,;:]{8,12}$"

    private var fullNamePattern = Pattern.compile(fullNameRegex)
    private var passwordPattern = Pattern.compile(passwordRegex)

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun isFullName(fullName: String): Bundle {
        var bundle = Bundle()

        when {

            fullName.length < 3 -> {
                bundle.putBoolean("status", false)
                bundle.putInt("reason", 3)

            }
            fullName.length > 200 -> {
                bundle.putBoolean("status", false)
                bundle.putInt("reason", 200)
            }

            fullNamePattern.matcher(fullName).matches() -> {

                bundle.putBoolean("status", true)
            }
            else -> {
                bundle.putBoolean("status", false)
                bundle.putInt("reason", 0)
            }
        }


        return bundle
    }

    fun isPasswordValid(password: String): Boolean {
        return passwordPattern.matcher(password).matches()
    }

    fun isValidPhoneNumber(phNumb: String, cCode: String): Boolean {
        val phoneUtil = PhoneNumberUtil.getInstance()
        return try {
            val ph = phoneUtil.parse(phNumb, cCode)
            phoneUtil.isValidNumber(ph)
        } catch (e: NumberParseException) {
            e.printStackTrace()
            false
        }
    }


    fun isPassowrdMatch(firstPass: String, secondPass: String): Boolean {
        return firstPass == secondPass
    }


}