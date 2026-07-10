//
// Created by Abhinav Ranjan on 08/07/26.
//

import Foundation
import SharedLogic

@MainActor
final class SplashObservable: BaseObservable {

    @Published
    private(set) var state: StartupState

    private let presenter: StartupPresenter

    init(
        presenter: StartupPresenter
    ){
        self.presenter = presenter
        self.state = presenter.state.value

        super.init()
    }

    func onAppear() {
        presenter.onAppStarted()
    }
}
