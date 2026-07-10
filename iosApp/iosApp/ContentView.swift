import SwiftUI
import SharedLogic

struct ContentView: View {

    var body: some View {

        let state = LoginState(
            email: "abc@xyz.com",
            emailError: nil,
            password: "",
            passwordError: nil,
            isLoading: false,
            error: nil,
            user: nil
        )

        return Text(state.email)
    }
}
