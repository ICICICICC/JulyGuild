import com.github.julyss2019.mcsp.julyguild.placeholder.Placeholder;
import com.github.julyss2019.mcsp.julyguild.placeholder.PlaceholderText;
import com.github.julyss2019.mcsp.julyguild.util.Util;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        for (ItemFlag itemFlag : ItemFlag.values()) {
            System.out.println(itemFlag.name());
        }
    }
}
