package com.streamability.datalayer.utils

class Validation {
    fun validateForm(inputForm: String, string: String): String{
        when (inputForm) {
            "username" -> {
                if (isAlphaNumeric(string) && isRange8To25(string) && !isSpecialCharacter(string)){
                    return "Username works!"
                }
                else if (!isRange8To25(string)){
                    return "Username must be between 8 and 25 characters long"
                }else if (!isAlphaNumeric(string)){
                    return "Username can only contain letters and numbers"
                }else if (isSpecialCharacter(string)){
                    return "Usernames can not contain Special Characters like !#$%@^&_."
                }
                return "Username Not Valid"
            }
            "password" -> {
                if (isAlphaNumeric(string) && isRange8To25(string) && isSpecialCharacter(string)){
                    return "Password works!"
                }
                else if (!isRange8To25(string)){
                    return "Password must be between 8 and 25 characters long"
                }else if (!isAlphaNumeric(string)){
                    return "Password must contain letters and numbers"
                }else if (!isSpecialCharacter(string)){
                    return "Password must contain at least 1 special character"
                }
                return "Password Not Valid"
            }
            else -> {
                return "Input Field not recognized"
            }
        }
    }

    private fun isAlphaNumeric(string: String): Boolean{
        return Regex.ALLOWED_CHAR.toRegex().containsMatchIn(string)
    }
    private fun isRange8To25(string: String): Boolean{
        return Regex.MIN_MAX_LENGTH_8_20.toRegex().containsMatchIn(string)
    }
    private fun isSpecialCharacter(string: String): Boolean{
        return Regex.NON_ALPHA_NUM.toRegex().containsMatchIn(string)
    }

    companion object Regex {
        const val ALLOWED_CHAR = "[a-zA-Z0-9]"
        const val MIN_MAX_LENGTH_8_20 = "(?=.{8,25}\$)"
        const val NON_ALPHA_NUM = "('\'W)+"
    }
}