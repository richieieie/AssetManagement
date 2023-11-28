package data;

import utils.Inputter;
import utils.Visual;

import java.time.LocalDateTime;

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

    public void searchRequestsByEID(String eID) {
        RequestList cpyR = new RequestList();
        cpyR.addAll(this.stream().filter(r -> r.getEmployeeId().equals(eID)).toList());

        cpyR.showAll();
    }

    public boolean sendNew(Employee em, AssetList assetList) {
        assetList.showAll();

        // Prompt employee to enter an assetId and check it
        String assetID = Inputter.getString("ID (A000): ", "[Aa]\\d{3}", "Please enter with " +
                "(A000) " + "format").toUpperCase();
        if (assetList.objectNotFound(assetID))
            return false;
        Asset asset = assetList.searchById(assetID);

        int amount = Inputter.getInt("Amount: ", 0, asset.getQuantity());
        if (amount == 0)
            return false;

        // Generate unique id for req
        String reqId = Inputter.generateUniqueId("R", 3, this);
        LocalDateTime time = LocalDateTime.now();

        return this.addNew(new Request(reqId, assetID, em.getId(), amount, time));
    }

    public boolean cancelOne(Employee em) {
        this.searchRequestsByEID(em.getId());

        String id = Inputter.getString("ID (R000): ", "[Rr]\\d{3}", "Please enter with " +
                "(R000) " + "format").toUpperCase();
        Request req = this.searchById(id);

        // If request is not found or employee ID is different, return false
        if (this.objectNotFound(id) || !req.getEmployeeId().equals(em.getId()))
            return false;

        return this.deleteOne(req);
    }

    public boolean returnOne(Employee em, AssetList assetList, BorrowList borrowList) {
        borrowList.searchBorrowsByEID(em.getId());

        String bID = Inputter.getString("ID (B000): ", "[Bb]\\d{3}", "Please enter with " +
                "(B000) " + "format").toUpperCase();
        Borrow borrow = borrowList.searchById(bID);

        // If borrow request is not found or employee ID is different, return false
        if (borrowList.objectNotFound(bID) || !borrow.getEmployeeId().equals(em.getId()))
            return false;

        if (!assetList.updateAssetQuantity(borrow))
            return false;

        return borrowList.deleteOne(borrow);
    }

    public boolean approveOne(AssetList assetsSource, BorrowList borrowsSource) {
        this.showAll();

        // Find request by ID
        String id;
        id = Inputter.getString("ID (R000): ", "[Rr]\\d{3}", "Please enter with " +
                "(R000) " + "format").toUpperCase();
        if (objectNotFound(id)) {
            System.out.println("Request doesn't exist");
            return false;
        }
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
