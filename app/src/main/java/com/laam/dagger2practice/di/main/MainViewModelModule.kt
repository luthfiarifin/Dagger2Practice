package com.laam.dagger2practice.di.main

import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.di.ViewModelKey
import com.laam.dagger2practice.ui.main.post.PostViewModel
import com.laam.dagger2practice.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(postViewModel: PostViewModel):ViewModel
}
