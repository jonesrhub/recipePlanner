class Ingredients:
    itemName = ""
    itemAmount = ""

    def __init__(self, itemName , itemAmount):
        self.itemName = itemName
        self.itemAmount = itemAmount

    def getIngredient(self):
        return self.itemAmount + self.itemName
