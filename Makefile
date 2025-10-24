MAIN_CLASS = ro.academyplus.avaj.simulator.Simulator
SCENARIO_FILE = scenario.txt

all: compile run

compile:
	@echo "Finding source files..."
	find . -name "*.java" > sources.txt

	@echo "Compiling project..."
	javac @sources.txt
	@echo "Compilation successful."

run:
	@echo "Running simulator..."
	java $(MAIN_CLASS) $(SCENARIO_FILE)

clean:
	@echo "Cleaning project..."
	find . -type f -name "*.class" -delete
	rm -f simulation.txt
	rm -f sources.txt

re: clean all

.PHONY: all compile run clean re
