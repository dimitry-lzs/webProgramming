package com.webProgramming.models.enums;

import com.webProgramming.src.AdminPages;
import com.webProgramming.src.ClientPages;
import com.webProgramming.src.SellerPages;

public enum UserType {
    ADMIN(AdminPages.ADMIN_MENU),
    SELLER(SellerPages.SELLER_MENU),
    CLIENT(ClientPages.CLIENT_MENU);

    private final String redirectPath;

    UserType(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    public String getRedirectPath() {
        return redirectPath;
    }
}
