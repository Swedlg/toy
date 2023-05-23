package com.swedlg.thinkingofyou;

import com.swedlg.thinkingofyou.Room.User;

public interface AdapterListener {

    void onUpdate(User user);
    void onDelete(int id, int pos);
}
