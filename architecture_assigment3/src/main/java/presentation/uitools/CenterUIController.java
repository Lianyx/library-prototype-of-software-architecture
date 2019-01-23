package presentation.uitools;

import lombok.Data;
import presentation.mainpageui.RootUIController;

@Data
public abstract class CenterUIController {
    protected RootUIController root;

    public abstract void instanceInit(RootUIController root);
}
