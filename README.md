# APU Online Order Delivery System

## Overview

This project is an online order and delivery management system for APU. It is structured as a multi-module Java EE application, consisting of EJB and WAR modules. The system allows customers to place orders, staff to manage products and deliveries, and administrators to oversee operations.

## Project Structure

- **APU Online Order Delivery System-ejb/**: Contains Enterprise JavaBeans (EJB) for business logic and data models.
- **APU Online Order Delivery System-war/**: Contains web resources (JSPs, HTML), servlets, and controllers for the web interface.
- **build/**: Compiled artifacts (JAR, WAR files) and build metadata.
- **src/**: Configuration files and manifests.
- **nbproject/**: NetBeans project configuration files.

## Key Features

- Customer registration, profile management, and order placement
- Staff management and assignment of delivery staff
- Product management (add, update, search)
- Order tracking and receipt generation
- Ratings and reviews for products and delivery staff
- Secure login and role-based access

## Technologies Used

- Java EE (EJB, JSP, Servlets)
- Ant build system
- NetBeans IDE project structure

## How to Build and Run

1. **Prerequisites:**
   - JDK 8 or above
   - Apache Ant
   - GlassFish or compatible Java EE server
   - NetBeans IDE (recommended)
2. **Build the project:**
   - Use Ant or NetBeans to build both EJB and WAR modules.
   - Artifacts will be generated in the `build/` directory.
3. **Deploy:**
   - Deploy the generated WAR and EJB JAR files to your Java EE server.
   - Access the web application via your server's URL.

## File Locations

- Web pages: `APU Online Order Delivery System-war/web/`
- EJB models: `APU Online Order Delivery System-ejb/src/java/model/`
- Configuration: `src/conf/`, `APU Online Order Delivery System-ejb/src/conf/`, `APU Online Order Delivery System-war/src/conf/`

## License

This project is for educational purposes at APU. Contact the project owner for usage permissions.

## Author

- [Your Name]
- [Contact Information]
