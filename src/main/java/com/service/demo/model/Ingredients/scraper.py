
import urllib2
response = urllib2.urlopen('http://python.org/')

html = urlopen('https://www.olivemagazine.com/recipes/meat-and-poultry/chicken-and-leek-puff-pie/')

from bs4 import BeautifulSoup
soup = BeautifulSoup(html, "lxml")
# print(soup.prettify())

ingredientList = soup.find_all('span', class_="body-copy")
# child = ingredientList.contents
# for c in child:
# print(ingredientList)

listIng = []
for l in ingredientList:
    name = l.contents[1]
    listIng.append(name.contents[0])
    listIng.append(l.contents[2])

for i in listIng:
     print(i + "")

file = open("ingredients.txt", "w")
for i in listIng:
    file.write(i)
file.close()
     #
     # ing = Ingredient()
     # ing.setItemAmount(l.contents[2])
     # ing.setItemName(name.contents[0])
     # listIng.addIngredients(ing)
