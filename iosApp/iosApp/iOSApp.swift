import SwiftUI
import shared


@main
struct iOSApp: App {

    init(){
        KoinKt.doInitKoinIOS()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
