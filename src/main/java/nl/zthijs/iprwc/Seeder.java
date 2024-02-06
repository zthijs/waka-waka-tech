package nl.zthijs.iprwc;


import lombok.Data;
import nl.zthijs.iprwc.dao.CategoryDAO;
import nl.zthijs.iprwc.dao.ProductDAO;
import nl.zthijs.iprwc.dao.RoleDAO;
import nl.zthijs.iprwc.entity.Product;
import nl.zthijs.iprwc.entity.ProductCategory;
import nl.zthijs.iprwc.entity.Role;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Data
@Component
public class Seeder {

    private final RoleDAO roleDAO;
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;

    @EventListener
    public void seed(ContextRefreshedEvent event) {

        this.seedRolesIfNoneExist();
        this.seedProductsWithCategoriesIfNoneExist();
    }


    public void seedProductsWithCategoriesIfNoneExist(){
        if(productDAO.count() == 0 && categoryDAO.count() == 0) {
            System.out.println("Found no products and no categories, seeding...");

            // Create Network Switches category
            ProductCategory switches = new ProductCategory(
                    "c26ae555-04f4-56f1-9c74-930196d04ef1",
                    "Network switches",

                    "With a network switch, you can create even more wired connections in your network. That means you can use a switch to connect more devices via a network cable. You can connect a basic network switch quickly and easily. These Ethernet switches are plug and play. Do you want to change settings? If you choose a managed switch, you can adapt it to your preferences.",
                    "https://images.unsplash.com/photo-1551703599-2a53f65da7e7?q=80&w=2073&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "switches",
                    null
            );

            // Save Network Switches category
            categoryDAO.saveCategory(switches);

            // Create Network Switches products
            Arrays.asList(
                    new Product(
                            "TP-Link TL-SG1016PE",
                            "Provide your network at home or at the office with 16 extra network connections with the TP-Link TL-SG1016PE switch. 8 of the network ports on this switch have Power over Ethernet. That means you can power access points or IP cameras via the network cable. In addition, you can configure the switch via the web browser. This way, you can prioritize important network traffic like video calls.",
                            new BigDecimal(179),
                            "https://image.coolblue.nl/max/500x500/products/1406347"
                    ),
                    new Product(
                            "TP-Link TL-SG108PE",
                            "The TP-Link TL-SG108PE is a managed switch with 4 PoE connectors. Use these ports to power other PoE devices, such as IP cameras, access points, or IP phones. The GS108Pe also has business network functions that you can easily configure yourself.",
                            new BigDecimal(61),
                            "https://image.coolblue.nl/max/500x500/products/762513"
                    ),
                    new Product(
                            "Netgear GS116LP",
                            "The Netgear GS116PP is a switch with Power over Ethernet+. This way, you can power devices such as IP cameras or thin clients via the 16 network connectors on this network switch. The switch is plug and play, so you can add it to an existing network in just a few minutes. The device is energy-efficient as well.",
                            new BigDecimal(169),
                            "https://image.coolblue.nl/max/500x500/products/1426120"
                    ),
                    new Product(
                            "Netgear GS105",
                            "The NETGEAR GS105 is a network switch with 5 network ports with a maximum speed of 1Gbps. This switch is plug and play, so you can add multiple devices to your home network without configuration. You can easily mount the switch under a desk or on a wall.",
                            new BigDecimal("26.99"),
                            "https://image.coolblue.nl/max/500x500/products/1359894"
                    )

            ).forEach(product -> {
                product.setCategory(switches);
                productDAO.saveProduct(product);
            });

            // Create Routers category
            ProductCategory routers = new ProductCategory(
                    "85560a45-a218-512c-9c59-bfce25389bda",
                    "Routers",
                    "A router provides wireless internet or a wired connection in your home or office. With a router, you can connect computers, TVs, tablets, and smartphones to the network. This way, you can stream movies, download, or surf the web wherever you are.",
                    "https://images.unsplash.com/photo-1525004277915-9e455d05ca5d?q=80&w=1932&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "routers",
                    null

            );

            // Save Routers category
            categoryDAO.saveCategory(routers);

            // Create Routers products
            Arrays.asList(new Product(
                        "TP-Link Deco XE75 Pro 3-pack",
                        "The TP-Link Deco XE75 Pro 3-pack is a mesh WiFi system that ensures fast WiFi in your entire house. This WiFi system has 2.5Gbps network connectors, so you can also use these with the fastest internet connections for at home. Thanks to WiFi 6E, this mesh system supports the 6GHz frequency band for WiFi.",
                        new BigDecimal(469),
                        "https://image.coolblue.nl/max/500x500/products/1831741"
                    ),new Product(
                            "TP-Link Archer AXE75",
                            "The TP-Link Archer AXE75 is a fast and stable WiFi 6E router. This router supports the 6GHz frequency band, so you can set up a faster connection. In addition, you'll have less interference with the WiFi of your neighbors on this frequency band. This way, you can quickly go online in a larger part of your home. The router also supports other frequency bands, so you always have a good connection.",
                            new BigDecimal(199),
                            "https://image.coolblue.nl/max/500x500/products/1774792"
                    ),new Product(
                            "Asus RT-AX86U Pro",
                            "You can provide all your games with the fastest WiFi connection with the Asus RT-AX86U gaming router. This router has the fastest WiFi version, WiFi 6, so you can go online up to 5 times faster than with a wireless AC router. On top of that, the router has 1 extremely fast wired connection of 2.5Gbps. This is 2.5 times faster than usual.",
                            new BigDecimal(239),
                            "https://image.coolblue.nl/max/500x500/products/1463865"
                    ),new Product(
                            "TP-Link Deco X50 Mesh WiFi 6 PoE 3-pack",
                            "Provide functional internet to every corner of your home with the Deco X50 PoE routers. You can connect an Ethernet cable to the port with 2.5Gbps transfer speed. Your TP-Link mesh router shares this data to all your devices via 2 frequencies. The routers don't need a power cord, thanks to PoE.",
                            new BigDecimal(339),
                            "https://image.coolblue.nl/max/500x500/products/1894184"
                    ),new Product(
                            "TP-Link M7350",
                            "Travel a lot and sick of bad WiFi connection? Create your own WiFi hotspot with 4G connection across Europe with the TP-Link M7350. Its compact size ensures you can easily take it anywhere. You can see all important information on the screen, like the battery life, connection status, and data consumption.",
                            new BigDecimal(79),
                            "https://image.coolblue.nl/max/500x500/products/1364498"
                    )
            ).forEach(product -> {
                product.setCategory(routers);
                productDAO.saveProduct(product);
            });

            // Create Access Points category

            ProductCategory accessPoints = new ProductCategory(
                    "fcef18db-5f21-5752-9cc4-0d3177bf5cb1",
                    "Access points",
                    "With an access point, you can create a wireless network in a large area. This is useful if you want to provide a large office or a large house with a wireless network. You can also use an access point to expand your existing network. This way, you can create a wireless network in a place where you don't have a good wireless signal yet.",
                    "https://images.unsplash.com/photo-1516044734145-07ca8eef8731?q=80&w=2073&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "access-points",
                    null
            );

            // Save Access Points category
            categoryDAO.saveCategory(accessPoints);

            // Create Access Points products
            Arrays.asList(new Product(
                    "Ubiquiti UniFi 6 Professional 2-Pack",
                    "The Ubiquiti AP-U6-Pro 2-Pack is a set of access points for companies where all employees need a reliable WiFi connection. Thanks to WiFi 6, you can go online with over 300 devices at the same time without the wireless network becoming unreliable. The speed is 4 times higher than with WiFi 5, so all of your employees can work hybrid without any issues.",
                    new BigDecimal(355),
                    "https://image.coolblue.nl/max/500x500/products/1680375"
            ),new Product(
                    "Netgear WAX620",
                    "The Netgear WAX620 is a WiFi 6 access point for small and medium-sized companies. This access point has the newest version of WiFi, called WiFi 6. This way, the speed of your wireless network is 40% faster. You can also connect 4 times as many devices, while your network stays stable.",
                    new BigDecimal(250),
                    "https://image.coolblue.nl/max/500x500/products/1593562"
            ),new Product(
                    "KPN SuperWifi Access Point",
                    "Increase the range of your internet connection with the KPN SuperWifi access points. KPN SuperWifi points work with WiFi 6, so you have fewer problems with interference and your connection is always stable. Connect this station to your modem or router with an internet cable and the access point wirelessly transmits the signal to all nearby devices.",
                    new BigDecimal(99),
                    "https://image.coolblue.nl/max/500x500/products/1916375"
            ),new Product(
                    "TP-Link Omada EAP650 2-pack",
                    "You can provide your office with a stable WiFi 6 connection with the TP-Link Omada EAP650 2-pack access points. This set of 2 WiFi access points supports WiFi 6, so you can set up a more stable connection. Even with more than 30 employees who connect their device to WiFi at the same time.",
                    new BigDecimal(312),
                    "https://image.coolblue.nl/max/500x500/products/1752528"
            ),new Product(
                    "TP-Link Omada EAP245 3-pack",
                    "Provide a small company or pub with wireless internet with the TP-Link EAP245 3-pack Access Points. You can easily set up these access points via the TP-Link software. You can connect the access points from this set with each other. This way, you get WiFi coverage in a larger part of your company. ",
                    new BigDecimal(325),
                    "https://image.coolblue.nl/max/500x500/products/1262158"
            ),new Product(
                    "Ubiquiti Unifi UAP-AC-M 2-pack",
                    "The Ubiquiti UniFi UAP-AC-M 2-pack is a set of 2 waterproof access points that you can use for WiFi at a company property, terrace, or recreational area. You can link these access points together to create one large mesh network with good coverage. The access points communicate with each other, so your devices will always be connected to the strongest station. ",
                    new BigDecimal(197),
                    "https://image.coolblue.nl/max/500x500/products/1722180"
            )).forEach(product -> {
                product.setCategory(accessPoints);
                productDAO.saveProduct(product);
            });

            ProductCategory accessories = new ProductCategory(
                    "dbdc0f9b-183b-518a-afa9-a43fec932238",
                    "Accessories",
                    "A wide range of accessories for your computer, laptop, tablet, or smartphone. From laptop bags to laptop stands and from tablet covers to smartphone cases. You'll find all the accessories you need for your device here.",
                    "https://images.unsplash.com/photo-1625276254563-f0fbbf66a5e7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "accessories",
                    null
            );

            // Save Accessories category
            categoryDAO.saveCategory(accessories);

            // Create Accessories products
            Arrays.asList(new Product(
                        "BlueBuilt Network cable STP CAT6 0.5m black",
                        "Connect devices to your router or powerline adapter with the BlueBuilt Network Cable STP CAT6 0.5 Meter Black. You can connect devices with a maximum speed of 10Gbps with these network cables. Every STP cable is shielded with a metal mesh, so you won't be bothered by interfering signals.",
                        new BigDecimal("13.99"),
                        "https://image.coolblue.nl/max/500x500/products/1792489"
                    ),new Product(
                            "BlueBuilt Network Cable STP CAT6 5m White",
                            "Connect devices to your router or powerline adapter with the BlueBuilt Network Cable STP CAT6 5m White. You can connect devices with a maximum speed of 10Gbps with this network cable.",
                            new BigDecimal("16.99"),
                            "https://image.coolblue.nl/max/500x500/products/1757434"
                    ),new Product(
                            "Netgear Nighthawk A7000",
                            "Make your computer suitable for fast, wireless internet with the Netgear Nighthawk A7000 WiFi adapter. Connect the adapter to a USB 3.0 port and surf with a maximum speed of 1,300Mbps, which is more than enough to stream a movie in 4K over your home network, while downloading.",
                            new BigDecimal(69),
                            "https://image.coolblue.nl/max/500x500/products/1371784"
                    ),new Product(
                            "TP-Link Archer T9UH",
                            "Provide your computer with wireless internet using the TP-Link Archer T9UH WiFi Adapter. After installation, you can browse wirelessly with a speed of up to 1,300Mbps. That is more than enough to stream a movie in Ultra HD over your home network and download updates at the same time.",
                            new BigDecimal(59),
                            "https://image.coolblue.nl/max/500x500/products/1370706"
                    ),new Product(
                            "TP-Link Archer T3U",
                            "With the TP-Link Archer T3U WiFi adapter, you can make any computer suitable for a fast wireless connection. Plug the compact adapter into a USB port and you can go online wirelessly with a maximum speed of 867Mbps. More than enough to stream a series and download at the same time.",
                            new BigDecimal("24.99"),
                            "https://image.coolblue.nl/max/500x500/products/1381688"
                    )
            ).forEach(product -> {
                product.setCategory(accessories);
                productDAO.saveProduct(product);
            });

        }
    }

    public void seedRolesIfNoneExist(){
        if(roleDAO.count() == 0){
            System.out.println("Found no roles, seeding...");
            roleDAO.save(new Role("USER", "A basic application user"));
            roleDAO.save(new Role("ADMIN", "An administrator"));
        }
    }

}
