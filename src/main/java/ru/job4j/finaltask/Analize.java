package ru.job4j.finaltask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        Map<Integer, String> old = previous.parallelStream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (User user : current) {
            Integer key = user.getId();
            if (old.containsKey(key)) {
                if (!old.get(key).equals(user.getName())) {
                    rsl.addChanged();
                } else {
                    rsl.addStable();
                }
            } else {
                rsl.addAdded();
            }
        }

        rsl.setDeleted(previous.size() - rsl.results()[1] - rsl.getStable());
        return rsl;
    }
}
