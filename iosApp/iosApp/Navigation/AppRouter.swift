//
// Created by Abhinav Ranjan on 08/07/26.
//

import SwiftUI

@MainActor
final class AppRouter : ObservableObject {
    @Published
    var destination: AppDestination = .splash

    func navigate(to destination: AppDestination){
        self.destination = destination
    }
}

