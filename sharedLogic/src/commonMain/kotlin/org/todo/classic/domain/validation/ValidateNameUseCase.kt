package org.todo.classic.domain.validation

class ValidateNameUseCase {

    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()){
            return ValidationResult(
                errorMessage = "Name is required",
                successful = false
                )
        }
        if (name.length < 2){
            return ValidationResult(
                errorMessage = "Name must be at least 2 characters",
                successful = false
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}