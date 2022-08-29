from decimal import Decimal

stock = ["味王", "聯華", "愛之味", "瑞昱", "陽明"]
buyPrice = [33.45, 57.6, 9.83, 352, 90.7]
sellPrice = [35, 56.7, 10.11, 355, 89.1]
qty = [1000, 3000, 7000, 10000, 5000]

# Define new round function, because python is use bankers' rounding.
def round_v2(num):
	result = Decimal(num).quantize(Decimal(1.), rounding = "ROUND_HALF_UP")
	return int(result)

# Unrealized gains and losses
for i in range(0, 5):
	buyAmt = round_v2(buyPrice[i] * qty[i])
	buyFee = round_v2(buyAmt * 0.001425)
	cost = buyAmt + buyFee

	sellAmt = round_v2(sellPrice[i] * qty[i])
	sellFee = round_v2(sellAmt * 0.001425)
	sellTax = round_v2(sellAmt * 0.003)
	income = sellAmt - sellFee - sellTax
	print("{name}：未實現損益 = [{income} - {cost}] = {ugal}元".format(name = stock[i], income = income, cost = cost, ugal = income - cost))
