import SwiftUI

struct ContentView: View {
   @StateObject
    private var router = AppRouter()

    var body: some View {
        switch router.destination {
        case .splash:
            Text("Splash")

        case .login:
            Text("Login")

        case .register:
            Text("Register")

        case .dashboard:
            Text("Dashboard")
        }
    }
}