# Usage

## Compile and Run

- `scripts/compile.bat`
- `scripts/run.bat`

## Import Data

- Place CSVs in `test-data/`.
- Use CLI: Import/Export menu, then choose Import Students or Courses.

## Demo Flow

- Manage Students: add a couple of students
- Manage Courses: add a few courses
- Enrollment: enroll students with max credits rule
- Grades: record marks -> transcripts
- Export: write students/courses/enrollments to `exports/`
- Backup: copy exports to timestamped `backups/` and show total size
- Advanced Features Demo: demonstrates all Java concepts (option 7)

## CSV formats

- students.csv: id,regNo,fullName,email,status
- courses.csv: code,title,credits,instructor,semester,department
