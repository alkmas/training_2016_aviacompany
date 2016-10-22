TEMPLATE_INSERT = "INSERT INTO schedule VALUES('{0[0]}','{0[1]}','{0[2]}','{0[3]}','{0[4]}','{0[5]}');"
for line in open("schedule.txt", encoding="utf-8"):
	if not line.startswith("Рейс"):
		line = line.strip()
		if ">" in line:
			way = ";".join(e.strip() for e in line.split(">"))
			continue
		else:
			line = ",".join(e.strip() for e in line.split(","))
			flight = way + ";" + ";".join(line.split())
			flight = flight.split(';')
			if len(flight)==6:
				print(TEMPLATE_INSERT.format(flight))