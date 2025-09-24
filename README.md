# Campus Course & Records Manager (CCRM)

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-green.svg)]()

Video Link: https://drive.google.com/file/d/1cE57LpWZVTActS4UH1tbELyc1VYGIZ8b/view?usp=drivesdk

## 🎓 Project Overview

The **Campus Course & Records Manager (CCRM)** is a comprehensive console-based Java SE application designed to manage academic records for educational institutions. Built as a demonstration of advanced Java programming concepts, CCRM showcases professional software development practices while providing practical functionality for managing students, courses, enrollments, and academic records.

### ✨ Key Features

- **🎓 Academic Management**: Complete CRUD operations for students, courses, and enrollments
- **📊 Grade Management**: Grade recording, GPA calculation, and transcript generation
- **📁 File Operations**: CSV import/export with automated backup system
- **🔧 Advanced Java**: Demonstrates all core Java SE concepts and design patterns
- **💼 Professional Quality**: Enterprise-level code structure and documentation

## 🚀 Quick Start

### Prerequisites

- **Java 17+** installed and configured
- **Windows/Linux/macOS** command line access
- **Git** for cloning the repository

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/CodeWithAmrat/CCRM.git
   cd CCRM
   ```

2. **Verify Java installation**
   ```bash
   java -version
   javac -version
   ```
   Should show Java 17 or higher.

3. **Compile the project**
   ```bash
   # Windows
   scripts\compile.bat
   
   # Linux/macOS
   chmod +x scripts/compile.sh
   ./scripts/compile.sh
   ```

4. **Run the application**
   ```bash
   # Windows
   scripts\run.bat
   
   # Linux/macOS
   ./scripts/run.sh
   ```

### First Run Demo

1. **Import sample data**:
   - Choose option `4` (Import/Export Data)
   - Choose option `1` (Import Students)
   - Choose option `2` (Import Courses)

2. **Explore features**:
   - Option `1`: View students
   - Option `2`: View courses
   - Option `3`: Enrollment demo
   - Option `7`: Advanced features demo

3. **Export and backup**:
   - Option `4` → `3`: Export all data
   - Option `5`: Create timestamped backup

## 📁 Project Structure

```
CCRM/
├── src/edu/ccrm/                    # Source code
│   ├── cli/                        # Command-line interface
│   │   └── Main.java               # Application entry point
│   ├── config/                     # Configuration management
│   │   └── AppConfig.java          # Singleton configuration
│   ├── domain/                     # Business entities
│   │   ├── Person.java             # Abstract base class
│   │   ├── Student.java            # Student entity
│   │   ├── Instructor.java         # Instructor entity
│   │   ├── Course.java             # Course with Builder pattern
│   │   ├── CourseCode.java         # Immutable value class
│   │   ├── Enrollment.java         # Enrollment entity
│   │   └── Transcript.java         # Transcript generation
│   ├── enums/                      # Enumerations
│   │   ├── Semester.java           # Semester enum
│   │   └── Grade.java              # Grade enum with points
│   ├── exceptions/                 # Custom exceptions
│   │   ├── DuplicateEnrollmentException.java
│   │   └── MaxCreditLimitExceededException.java
│   ├── interfaces/                 # Service contracts
│   │   ├── Persistable.java        # Persistence interface
│   │   └── Searchable.java         # Generic search interface
│   ├── io/                        # File operations
│   │   └── FileIO.java             # NIO.2 file operations
│   ├── service/                   # Business logic
│   │   ├── StudentService.java     # Student management
│   │   ├── CourseService.java      # Course management
│   │   ├── EnrollmentService.java  # Enrollment logic
│   │   ├── ReportService.java      # Analytics and reporting
│   │   ├── EnhancedStudentService.java # Advanced features demo
│   │   └── TranscriptService.java  # Transcript management
│   ├── store/                     # Data persistence
│   │   └── DataStore.java         # Singleton data store
│   └── util/                      # Utility classes
│       └── BackupUtil.java        # Recursive backup utility
├── test-data/                     # Sample CSV files
│   ├── students.csv               # Sample student data
│   └── courses.csv                # Sample course data
├── scripts/                       # Build and run scripts
│   ├── compile.bat/.sh            # Compilation script
│   └── run.bat/.sh                # Execution script
├── exports/                       # Generated export files
├── backups/                       # Timestamped backups
├── screenshots/                   # Project screenshots
├── video/                         # Demo videos
├── README.md                      # This file
├── USAGE.md                       # Usage instructions
├── DEMO_SCRIPT.md                 # Demo video script
└── IMPLEMENTATION_SUMMARY.md      # Technical summary
```

## 🎯 Java Concepts Demonstrated

### Object-Oriented Programming
- ✅ **Encapsulation**: Private fields with controlled access
- ✅ **Inheritance**: Abstract Person → Student/Instructor hierarchy
- ✅ **Abstraction**: Abstract classes and methods
- ✅ **Polymorphism**: Method overriding and virtual invocation

### Advanced Language Features
- ✅ **Enums**: Semester and Grade with constructors/fields
- ✅ **Interfaces**: Persistable and Searchable<T> with default methods
- ✅ **Generics**: Type-safe collections and interfaces
- ✅ **Streams**: Functional programming with collections
- ✅ **Lambdas**: Functional interfaces and expressions
- ✅ **Method References**: Stream operations and callbacks

### Design Patterns
- ✅ **Singleton**: AppConfig and DataStore
- ✅ **Builder**: Course construction
- ✅ **Strategy**: Algorithm selection

### Control Structures
- ✅ **Enhanced Switch**: Rule-based switch statements
- ✅ **All Loop Types**: for, while, do-while, enhanced-for
- ✅ **Jump Control**: break, continue, labeled breaks
- ✅ **Conditional Logic**: if/else, ternary operators

### Memory Management
- ✅ **Try-with-Resources**: Automatic resource management
- ✅ **Immutability**: Immutable value classes with defensive copying

### Exception Handling
- ✅ **Custom Exceptions**: Business-specific error types
- ✅ **Multi-catch**: Efficient exception handling
- ✅ **Assertions**: Runtime validation and debugging

### File I/O & NIO.2
- ✅ **Path API**: Modern file system navigation
- ✅ **Files Class**: High-level file operations
- ✅ **Stream I/O**: Efficient data processing
- ✅ **Recursion**: Directory traversal and processing

### Advanced Concepts
- ✅ **Anonymous Inner Classes**: Event handling and callbacks
- ✅ **Inner Classes**: Non-static nested classes
- ✅ **Nested Classes**: Static nested classes
- ✅ **Upcast/Downcast**: Type casting with instanceof checks
- ✅ **Method Overloading**: Multiple method signatures
- ✅ **Arrays Utilities**: Sorting, searching, and manipulation

## 📋 Usage Guide

### Basic Operations

1. **Student Management**
   - Add, list, update, and deactivate students
   - View student profiles and enrollment history
   - Search students by various criteria

2. **Course Management**
   - Manage course catalog with instructor assignments
   - Filter courses by department, semester, or instructor
   - Track course credits and prerequisites

3. **Enrollment System**
   - Enroll students in courses with business rule validation
   - Enforce maximum credits per semester
   - Prevent duplicate enrollments

4. **Grade Management**
   - Record letter grades for enrolled students
   - Calculate GPA with weighted credits
   - Generate comprehensive transcripts

5. **File Operations**
   - Import student and course data from CSV files
   - Export current data to CSV format
   - Create timestamped backups with recursive size calculation

### Advanced Features Demo

Choose option `7` in the main menu to see demonstrations of:
- All control structures (loops, switches, jumps)
- Arrays utilities (sorting, searching, manipulation)
- Type casting and instanceof checks
- Anonymous inner classes for event handling
- Immutable classes with defensive copying

## 🔧 Technical Architecture

### Core Components

#### Domain Layer
- **Person** (Abstract): Base class demonstrating inheritance
- **Student**: Student entity with enrollment tracking
- **Instructor**: Faculty member management
- **Course**: Course catalog with Builder pattern
- **Enrollment**: Student-course relationship
- **Transcript**: Academic record generation

#### Service Layer
- **StudentService**: Student management operations
- **CourseService**: Course catalog management
- **EnrollmentService**: Enrollment business logic
- **ReportService**: Analytics and reporting
- **EnhancedStudentService**: Advanced feature demonstrations

#### Infrastructure Layer
- **DataStore**: In-memory data persistence (Singleton)
- **FileIO**: File import/export operations
- **BackupUtil**: Backup and recovery utilities
- **AppConfig**: Application configuration (Singleton)

### Business Logic

#### Enrollment Rules
- Maximum credits per semester enforcement
- Duplicate enrollment prevention
- Student status validation
- Course availability checking

#### Grade Management
- Letter grade to point conversion
- GPA calculation with weighted credits
- Transcript generation
- Academic progress tracking

## 📊 Data Formats

### CSV Import/Export

**Students CSV Format:**
```csv
id,regNo,fullName,email,status
S1,REG1001,Alice Johnson,alice@example.com,ACTIVE
S2,REG1002,Bob Smith,bob@example.com,ACTIVE
```

**Courses CSV Format:**
```csv
code,title,credits,instructor,semester,department
CS101,Intro to CS,4,Dr. Ada,FALL,CSE
MA101,Calculus I,4,Dr. Euler,SPRING,MAT
```

## 🛠️ Development Setup

### IDE Configuration

#### Eclipse
1. Create new Java project
2. Set source folder to `src/`
3. Configure build path
4. Run `Main.java` as Java application

#### IntelliJ IDEA
1. Open project folder
2. Configure SDK to Java 17+
3. Mark `src` as sources root
4. Run `Main.java`

#### VS Code
1. Install Java Extension Pack
2. Open project folder
3. Configure Java runtime
4. Run `Main.java`

### Build Configuration

The project uses simple batch/shell scripts for compilation:
- **Windows**: `scripts\compile.bat`
- **Linux/macOS**: `scripts\compile.sh`

No external dependencies or build tools required.

## 📚 Documentation

- **[USAGE.md](USAGE.md)**: Detailed usage instructions
- **[DEMO_SCRIPT.md](DEMO_SCRIPT.md)**: Video demo script
- **[IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md)**: Technical implementation details
- **Code Comments**: Comprehensive JavaDoc throughout

## 🎥 Demo Videos

- **Installation Guide**: `video/How to Install Java JDK.mp4`
- **Project Walkthrough**: `video/Readme.mp4`
- **Screenshots**: Available in `screenshots/` directory

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 👨‍💻 Author

**CodeWithAmrat**
- GitHub: [@CodeWithAmrat](https://github.com/CodeWithAmrat)
- Project: [CCRM Repository](https://github.com/CodeWithAmrat/CCRM)

## 🙏 Acknowledgments

- Java SE documentation and best practices
- Design patterns from Gang of Four
- Academic management system requirements
- Open source community contributions

---

**Project Status**: ✅ Complete and Production-Ready  
**Java Version**: 17+  
**Documentation**: Comprehensive  
**Testing**: Validated  
**Deployment**: Ready

*This project demonstrates comprehensive Java SE knowledge and professional software development practices.*