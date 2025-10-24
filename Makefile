MAIN_CLASS = ro.academyplus.avaj.simulator.Simulator

SCENARIO_FILE = scenario.txt

JAVA_FILES = $(shell find $(SRC_DIR) -name "*.java")

all: run

compile: $(JAVA_FILES)
	@echo "Finding source files..."
	find . -name "*.java" > sources.txt

	@echo "Compiling project..."
	javac @sources.txt
	@echo "Compilation successful."

run: compile
	@echo "Running simulator..."
	java $(MAIN_CLASS) $(SCENARIO_FILE)

clean:
	@echo "Cleaning project..."
	rm -rf *.class
	rm -f simulation.txt
	rm -f sources.txt

re: clean all

.PHONY: all compile run clean re
