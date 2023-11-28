package data;

import utils.IOExecutor;
import utils.Inputter;
import utils.Visual;

import java.util.List;
import java.util.Objects;

public class AssetList extends ObjectList<Asset> {
    public AssetList() {
        super();
    }

    public AssetList(String path) {
        super(path);
    }

    public void searchAssetsByName(String name) {
        AssetList cpyA = new AssetList();
        cpyA.addAll(this.stream().filter(a -> a.getName().toLowerCase().contains(name.toLowerCase())).toList());

        cpyA.showAll();
    }

    public Asset searchById(String id) {
        for (Asset a : this) {
            if (a.getId().equals(id))
                return a;
        }

        return null;
    }

    public int searchByIdInt(String id) {
        for (int i = 0, n = this.size(); i < n; i++) {
            if (get(i).getId().equals(id))
                return i;
        }

        return -1;
    }

    public boolean createNew() {
        // ID now will be auto generated uniquely
        String id = Inputter.generateUniqueId("A", 3, this);

        // Get others data from user
        String name = Inputter.getStringWithCap("Name: ");
        String color = Inputter.getStringWithCap("Color: ");
        int price = Inputter.getInt("Price: ", 1, Integer.MAX_VALUE);
        double weight = Inputter.getDouble("Weight: ", 1, 100);
        int quantity = Inputter.getInt("Quantity: ", 1, Integer.MAX_VALUE);

        return addNew(new Asset(id, name, color, price, weight, quantity));
    }


    @SuppressWarnings("unchecked")
    public boolean updateAsset() {
        this.showAll();

        // Get asset's id from user
        String id;
        id = Inputter.getString("ID (A000): ", "[Aa]\\d{3}", "Please enter with " +
                "(A000) " + "format").toUpperCase();
        if (objectNotFound(id)) {
            System.out.println("Asset doesn't exist");
            return false;
        }

        // Get update's info from user
        String name = Inputter.getStringEmpty("Name: ");
        String color = Inputter.getStringEmpty("Color: ");
        int price = Inputter.getIntEmpty("Price: ", 1, Integer.MAX_VALUE);
        double weight = Inputter.getDoubleEmpty("Weight: ", 1, 100);
        int quantity = Inputter.getIntEmpty("Quantity: ", 1, Integer.MAX_VALUE);

        int index = this.searchByIdInt(id);
        Asset a = this.get(index);

        // Validate info, if info is empty, return old value
        name = !name.isEmpty() ? name : a.getName();
        color = !color.isEmpty() ? color : a.getColor();
        price = price != -1 ? price : a.getPrice();
        weight = weight != -1 ? weight : a.getWeight();
        quantity = quantity != -1 ? quantity : a.getQuantity();

        // Create a copy for failure operation
        Asset cpyA = new Asset(a.getId(), name, color, price, weight, quantity);

        this.set(index, cpyA);

        // Try to write to file, if it fails, set back old values
        if (!IOExecutor.writeToFile(getPath(), (List) this)) {
            this.set(index, a);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean updateAssetQuantity(Request req) {
        int index = this.searchByIdInt(req.getAssetId());
        Asset a = this.get(index);

        // Make a copy of a for failure situation
        Asset cpyA = new Asset(a.getId(), a.getName(), a.getColor(), a.getPrice(), a.getWeight(),
                a.getQuantity() - req.getQuantity());

        this.set(index, cpyA);

        // Try to write to file, if it fails, set back old values
        if (!IOExecutor.writeToFile(getPath(), (List) this)) {
            this.set(index, a);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean updateAssetQuantity(Borrow borrow) {
        int index = this.searchByIdInt(borrow.getAssetId());
        Asset a = this.get(index);

        // Make a copy of a for failure situation
        Asset cpyA = new Asset(a.getId(), a.getName(), a.getColor(), a.getPrice(), a.getWeight(),
                a.getQuantity() + borrow.getQuantity());

        this.set(index, cpyA);

        // Try to write to file, if it fails, set back old values
        if (!IOExecutor.writeToFile(getPath(), (List) this)) {
            this.set(index, a);
            return false;
        }

        return true;
    }

    public boolean checkQuantity(Request req) {
        Asset a = searchById(req.getAssetId());

        if (Objects.isNull(a)) {
            System.out.println("Can't find the asset " + req.getAssetId());
            return false;
        }

        if (a.getQuantity() < req.getQuantity()) {
            System.out.println("Quantity is not enough");
            return false;
        }

        return true;
    }

    @Override
    public void showAll() {
        Visual.printDataList(this, new String[]{"ID", "Name", "Color", "Price", "Weight",
                "Quantity"}, "ASSETS");
    }

}
