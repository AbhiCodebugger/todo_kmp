package org.todo.classic.domain.validation

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "password is required"
            )
        }
        if (password.length < 6){
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 6 characters"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}