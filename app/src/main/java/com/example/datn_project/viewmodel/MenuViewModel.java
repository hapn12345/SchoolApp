package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.menu.Menu;
import com.example.datn_project.repositories.MenuRepository;

public class MenuViewModel extends ViewModel {
    private MenuRepository menuRepository;

    public MenuViewModel() {
        menuRepository = new MenuRepository();
    }

    public LiveData<Menu> getMenu(int id) {
        return menuRepository.getMenu(id);
    }

}
