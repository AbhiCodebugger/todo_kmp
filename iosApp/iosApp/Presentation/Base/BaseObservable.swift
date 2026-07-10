//
// Created by Abhinav Ranjan on 09/07/26.
//

import Foundation

@MainActor
class BaseObservable : ObservableObject {

    deinit {
        print("\(type(of:self)) deallocated")
    }
}