const tlacitloElement = document.getElementById("tlačítko");
const tabulkaMeals = document.getElementById("mealsTable");
const tabulkaMenus = document.getElementById("menusTable");
tlacitloElement.onclick = () => {
  const newRow = document.createElement("tr");

  tabulkaElement.appendChild(newRow);
};
const user = {
  name: "David",
  age: 21,
  salary: 250000.0,
  employees: [
    {
      name: "Vítek",
      age: 15,
    },
    {
      name: "Valentina",
      age: 12,
    },
  ],
};
console.log(user.employees);
fetch("http://localhost:8080/meals?id=4")
  .then((response) => response.text())
  .then((text) => {
    const listOfMeals = [];
    let totalPrice = 0;

    console.log(text);
    const rows = text.split("\n");
    rows.forEach((row) => {
      const cols = row.split(",");
      listOfMeals.push({
        id: parseInt(cols[0]),
        name: cols[1],
        price: parseFloat(cols[2]),
        discount: parseInt(cols[3]),
      });
    });
    listOfMeals.forEach((meal) => {
      const newRow = document.createElement("tr");
      const newColumn1 = document.createElement("td");
      newColumn1.innerText = meal.id;
      const newColumn2 = document.createElement("td");
      newColumn2.innerText = meal.name;
      const newColumn3 = document.createElement("td");
      newColumn3.innerText = meal.price;
      totalPrice = totalPrice + meal.price;
      const newColumn4 = document.createElement("td");
      newColumn4.innerText = meal.discount;
      newRow.appendChild(newColumn1);
      newRow.appendChild(newColumn2);
      newRow.appendChild(newColumn3);
      newRow.appendChild(newColumn4);
      tabulkaMeals.appendChild(newRow);

      console.log(meal["name"]);
    });

    const sumRow = document.createElement("tr");
    const sumColID = document.createElement("td");
    sumColID.innerText = "Total";
    const sumColName = document.createElement("td");
    const sumColPrice = document.createElement("td");
    sumColPrice.innerText = totalPrice;
    const sumColDiscount = document.createElement("td");
    sumRow.appendChild(sumColID);
    sumRow.appendChild(sumColName);
    sumRow.appendChild(sumColPrice);
    sumRow.appendChild(sumColDiscount);
    tabulkaMeals.appendChild(sumRow);
  });

fetch("http://localhost:8080/menus")
  .then((response) => response.text())
  .then((text) => {
    const listOfMenus = [];
    let totalPrice = 0;

    console.log(text);
    const rows = text.split("\n");
    rows.forEach((row) => {
      const cols = row.split(",");
      listOfMenus.push({
        id: parseInt(cols[0]),
        name: cols[1],
        price: parseFloat(cols[2]),
        discount: parseInt(cols[3]),
      });
    });

    listOfMenus.forEach((menu) => {
      const newRow = document.createElement("tr");
      const cols = ["id", "name", "price", "discount"];
      cols.forEach((col) => {
        const newColumn = document.createElement("td");
        newColumn.innerText = menu[col];
        newRow.appendChild(newColumn);
        if (col == "price") {
          totalPrice = totalPrice + menu.price;
        }
        console.log(col == "price");
      });
      tabulkaMenus.appendChild(newRow);
    });

    const sumRow = document.createElement("tr");
    const sumColID = document.createElement("td");
    sumColID.innerText = "Total";
    const sumColName = document.createElement("td");
    const sumColPrice = document.createElement("td");
    sumColPrice.innerText = totalPrice;
    const sumColDiscount = document.createElement("td");
    sumRow.appendChild(sumColID);
    sumRow.appendChild(sumColName);
    sumRow.appendChild(sumColPrice);
    sumRow.appendChild(sumColDiscount);
    tabulkaMenus.appendChild(sumRow);
  });
