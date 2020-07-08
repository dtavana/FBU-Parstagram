package com.dtavana.parstagram.models;

import com.parse.ParseFile;
import com.parse.ParseUser;

public class User {
    public static final String KEY_AVATAR = "avatar";

    public static ParseFile getAvatar() { return ParseUser.getCurrentUser().getParseFile(KEY_AVATAR); }

    public static void setAvatar(ParseFile avatar) { ParseUser.getCurrentUser().put(KEY_AVATAR, avatar); }
}
