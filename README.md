# The Golden Book · Back-office

Desktop back-office of **The Golden Book**, a platform for selling new and antique books. Employees sign in to manage the whole business: catalogue, authors, customers, employees, orders and reviews. Built with Java Swing on top of the shared [middleware](https://github.com/jorgeoleaa/thegoldenbook-middleware).

## Highlights

- **Full management of the business**: create, search, edit and delete books, authors, customers (with their addresses), employees, orders and reviews.
- **Employee authentication**, profile editing and password changes.
- **Paginated searches** over large tables, with selection dialogs to build orders from the catalogue.
- **Book cover uploads** with automatic thumbnail generation.
- **MVC with the Command pattern**: every user action is an `Action` class, keeping views free of business logic.
- **Custom Swing components**: table models, cell renderers and editors with inline actions.
- **Modern look and feel** with FlatLaf and layouts built with MigLayout.
- **Internationalisation** in Spanish, Galician and English.

## Tech stack

Java · Swing · FlatLaf · MigLayout · SwingX · JCalendar · Thumbnailator · Log4j 2 · Maven · MySQL

## Running locally

Requirements: JDK, Maven and MySQL.

1. Build and install the [middleware](https://github.com/jorgeoleaa/thegoldenbook-middleware) into your local Maven repository.
2. Configure the database and mail settings in `config/thegoldenbook-cfg.properties`.
3. Build and run:
   ```bash
   mvn clean package
   ```
   Then launch the generated JAR from the `target` folder.

## Related repositories

- [thegoldenbook-middleware](https://github.com/jorgeoleaa/thegoldenbook-middleware): business and data-access layer
- [thegoldenbook-web](https://github.com/jorgeoleaa/thegoldenbook-web): server-rendered web store (Servlets/JSP)
- [thegoldenbook-rest-api](https://github.com/jorgeoleaa/thegoldenbook-rest-api): REST API (JAX-RS)
