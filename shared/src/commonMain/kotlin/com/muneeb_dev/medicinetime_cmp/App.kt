package com.muneeb_dev.medicinetime_cmp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.muneeb_dev.medicinetime_cmp.UI.Navigation.NavigationScreens
import com.muneeb_dev.medicinetime_cmp.UI.Screens.ConfirmName_Screen
import com.muneeb_dev.medicinetime_cmp.UI.Screens.Home_Screen
import com.muneeb_dev.medicinetime_cmp.UI.Screens.Intro_Screen
import com.muneeb_dev.medicinetime_cmp.UI.Screens.MainScreen
import com.muneeb_dev.medicinetime_cmp.UI.Screens.MedicineDetails_Screen
import com.muneeb_dev.medicinetime_cmp.UI.Screens.Splash_Screen
import com.muneeb_dev.medicinetime_cmp.UI.Screens.ToStart_Screen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun App() {
    /// CMM poroject

        MaterialTheme {

            val navigator = rememberNavigator()

            NavHost(
                navigator = navigator,
                navTransition = NavTransition(),
                initialRoute = NavigationScreens.Splash.route

            ) {
                scene(
                    route = NavigationScreens.Splash.route,
                    navTransition = NavTransition()
                ) {
//                navigator.currentEntry
                    Splash_Screen(navigator )

                }
                scene(
                    route = NavigationScreens.Home.route,
                    navTransition = NavTransition()
                ) {
                    navigator.currentEntry
                    Home_Screen(navigator)
                }
                scene(
                    route = NavigationScreens.Intro.route,
                    navTransition = NavTransition()
                ) {
                    navigator.currentEntry
                    Intro_Screen(navigator)
                }
                scene(
                    route = NavigationScreens.ConfirmName.route,
                    navTransition = NavTransition()
                ) {

                    navigator.currentEntry
                    ConfirmName_Screen(navigator)
                }
                scene(
                    route = NavigationScreens.ToStart.route.plus(
                        NavigationScreens.ToStart.objectPath
                    ),
                    navTransition = NavTransition()
                ) { backStackEntry ->
                    val user_name: String? = backStackEntry.path<String>("username")
                    navigator.currentEntry
                    ToStart_Screen(navigator, user_name)
                }

                scene(
                    route = NavigationScreens.MainScreen.route.plus(
                        NavigationScreens.MainScreen.objectPath
                    ),
                    navTransition = NavTransition()
                ) { backStackEntry ->
//                    val user_name: String? = backStackEntry.path<String>("username")
//                    navigator.currentEntry
//                    MainScreen(navigator, user_name)

                    val current_screen: String? = backStackEntry.path<String>("current_screen")
                    navigator.currentEntry
                    MainScreen(navigator, current_screen)
                }

                scene(
                    route = NavigationScreens.MedicineDetails_Screen.route.plus(
                        NavigationScreens.MedicineDetails_Screen.objectPath
                    ),
                    navTransition = NavTransition()
                ) { backStackEntry ->
                    val medicinename: String? = backStackEntry.path<String>("medicinename")
                    navigator.currentEntry
                    if (medicinename != null) {
                        MedicineDetails_Screen(navigator, medicineName = medicinename  )
                    }
                }

//                scene(
//                    route = NavigationScreens.NewMedicine.route,
//                    navTransition = NavTransition()
//                ) {
//
//                    navigator.currentEntry
//                    NewMedicines_Screen(navigator)
//                }


            }
        }

    }
//}

//
//@Composable
//fun CustomScreen(
//    name: String,
//    state: YourStateType, // Replace YourStateType with the actual type of state
//    viewModel: YourViewModelType // Replace YourViewModelType with the actual type of viewModel
//) {
//    FKScaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            FKLargeTopAppBar(title = name)
//        },
//    ) { padding, triggerAccessoryView ->
//        ItemForm(
//            modifier = Modifier.padding(top = padding.calculateTopPadding()),
//            state = state,
//            dispatch = viewModel::dispatchEvent
//        )
//
//        LaunchedEffect(viewModel) {
//            viewModel.eventFlow
//                .filterIsInstance<ItemViewModel.Event.ChooseExpiration>()
//                .map { state.builder.expiration }
//                .onEach { selectedDate ->
//                    triggerAccessoryView(AccessoryViewTrigger.DatePicker(
//                        selectedDate = selectedDate ?: Clock.System.now().plus(3.days),
//                        minDate = Clock.System.now(),
//                        onResult = {
//                            viewModel.dispatchEvent(ItemViewModel.Event.OnExpirationSet(it))
//                        }
//                    ))
//                }.launchIn(this)
//        }
//    }
//}
