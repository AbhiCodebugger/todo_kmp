package org.todo.classic.domain.validation

class ValidateEmailUseCase {
        operator fun invoke(email: String): ValidationResult {
            if (email.isBlank()){
                return ValidationResult(
                    successful = false,
                    errorMessage = "Email is required"
                )
            }
            if (!email.contains("@")){
                return ValidationResult(
                    successful = false,
                    errorMessage = "Invalid Email address"
                )
            }
            return ValidationResult(
                successful = true
            )
        }
    }
