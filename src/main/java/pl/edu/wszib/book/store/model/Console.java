package pl.edu.wszib.book.store.model;

public enum Console {
    PS5("PlayStation", "5", "AMD Ryzen 7", "AMD Radeon", 512),
    PS4("PlayStation", "4", "AMD Ryzen 5", "AMD Radeon", 256),
    XBOX_ONE("Microsoft", "Xbox One", "AMD Ryzen 5", "AMD Radeon", 512);

    private String brand;
    private String model;
    private String cpu;
    private String gpu;
    private int disk;

    Console(String brand, String model, String cpu, String gpu, int disk) {
        this.brand = brand;
        this.model = model;
        this.cpu = cpu;
        this.gpu = gpu;
        this.disk = disk;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public int getDisk() {
        return disk;
    }
}
