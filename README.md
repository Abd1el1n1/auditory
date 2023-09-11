# DiffTool

`DiffTool` is a utility that helps in auditing changes between two instances of the same type. This tool provides the changes in a structured format, detailing which properties have changed and, in the case of lists, which items have been added or removed.

## Features
1. Tracks property updates, including nested properties.
2. Handles lists with add and remove operations.
3. Uses @AuditKey annotation or 'id' property to determine the unique key of items in a list.

## How it works:
- Uses Kotlin's reflection capabilities to introspect the properties of objects.
- If the properties are of type List, it checks for added or removed items.
- For each property, it recursively checks for differences, accumulating the path using dot notation.

## Time Taken:
Approximately 6 hours to design, implement, and test.
