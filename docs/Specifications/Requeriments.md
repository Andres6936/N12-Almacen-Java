## Requirements

Servidor

| Name    | R1 - List products                                                                                              |
|---------|-----------------------------------------------------------------------------------------------------------------|
| Summary | The administrator can see a complete list of the products in the warehouse and the available units of each one. |
| Input   |                                                                                                                 |
|         | None                                                                                                            |
| Output  |                                                                                                                 |
|         | List of available products and units                                                                            |

| Name    | R2 - Add products                                                                                                                                |
|---------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| Summary | The administrator adds a new product to the warehouse. The code indicated cannot be the same as any of the products that are already registered. |
| Input   |                                                                                                                                                  |
|         | New product code                                                                                                                                 |
|         | New product name                                                                                                                                 |
|         | Price of a unit of the new product                                                                                                               |
|         | Number of initial units of the new product available                                                                                             |
| Output  |                                                                                                                                                  |
|         | Added product to warehouse                                                                                                                       |

## List of Requirements - Outlet

Outlet

| Name    | R3 - Register a sale                                                                                                                                                                                                                                                                                                                             |
|---------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Summary | The point-of-sale manager adds a product to the purchase using your code, causing the inventory of each of the products in the shopping list to be updated. The total value shown for the purchase is also updated. If the code does not correspond to any product in the warehouse then nothing is added to the list and the error is reported. |
| Input   |                                                                                                                                                                                                                                                                                                                                                  |
|         | The code of the product being sold.                                                                                                                                                                                                                                                                                                              |
| Output  |                                                                                                                                                                                                                                                                                                                                                  |
|         | The inventory of the products in the purchase list was updated.                                                                                                                                                                                                                                                                                  |
