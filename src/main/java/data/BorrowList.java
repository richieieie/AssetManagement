package data;

import utils.Visual;

public class BorrowList extends ObjectList<Borrow> {
    public BorrowList() {
        super();
    }

    public BorrowList(String path) {
        super(path);
    }

    @Override
    public Borrow searchById(String id) {
        for (Borrow b : this) {
            if (b.getId().equals(id))
                return b;
        }

        return null;
    }

    @Override
    public void showAll() {
        Visual.printDataList(this, new String[]{"ID", "Asset ID", "Employee ID", "Quantity",
                "Time"}, "BORROWS");
    }
}
