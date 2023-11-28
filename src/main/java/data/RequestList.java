package data;

import utils.Inputter;
import utils.Visual;

public class RequestList extends ObjectList<Request> {
    public RequestList() {
        super();
    }

    public RequestList(String path) {
        super(path);
    }

    @Override
    public Request searchById(String id) {
        for (Request r : this) {
            if (r.getId().equals(id))
                return r;
        }

        return null;
    }

    public boolean approveOne(AssetList assetsSource, BorrowList borrowsSource) {
        // Find request by ID
        String id;
        do {
            id = Inputter.getString("ID (R000): ", "[Rr]\\d{3}", "Please enter with " +
                    "(R000) " + "format").toUpperCase();
            if (objectNotFound(id))
                System.out.println("Request doesn't exist");
        } while (objectNotFound(id));
        Request r = searchById(id);

        // Check asset quantity in assets source
        if (!assetsSource.checkQuantity(r))
            return false;

        // Insert data into borrow.dat
        boolean isBorrowAdded = borrowsSource.addNew(new Borrow(Inputter.generateUniqueId("B", 3, borrowsSource),
                r.getAssetId(), r.getEmployeeId(), r.getQuantity(), r.getTime()));
        if (!isBorrowAdded)
            return false;

        // Remove request in request.dat
        boolean isRequestDeleted = deleteOne(r);
        if (!isRequestDeleted)
            return false;

        // Update quantity of asset in assets source
        return assetsSource.updateAssetQuantity(r);
    }

    @Override
    public void showAll() {
        Visual.printDataList(this, new String[]{"ID", "Asset ID", "Employee ID", "Quantity",
                "Time"}, "REQUESTS");
    }
}
