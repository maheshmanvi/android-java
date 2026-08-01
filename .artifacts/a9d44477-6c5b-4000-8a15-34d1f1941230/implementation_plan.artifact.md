# Fix ListActivity and Toolbar Errors

The user is experiencing compilation errors in `ListActivity.java` due to a missing Toolbar in the layout and a syntax error in the back button logic. Additionally, there is a wrong import in `MainActivity.java` that will cause navigation issues.

## User Review Required

> [!IMPORTANT]
> I will be adding a `MaterialToolbar` to `activity_list.xml`. I am also adding a `RecyclerView` and `ProgressBar` to that layout as it is standard for a list-loading screen, ensuring the "List" activity is actually functional.

## Proposed Changes

### UI Components

#### [MODIFY] [activity_list.xml](file:///C:/Users/Dell/AndroidStudioProjects/ApiSample/app/src/main/res/layout/activity_list.xml)
- Add `MaterialToolbar` with ID `toolbar`.
- Add `RecyclerView` with ID `rvUsers` for displaying the user list.
- Add `ProgressBar` with ID `progressBar` for showing loading state.

### Activity Logic

#### [MODIFY] [ListActivity.java](file:///C:/Users/Dell/AndroidStudioProjects/ApiSample/app/src/main/java/com/example/apisample/ui/list/ListActivity.java)
- Fix `getOnBackPressedDispatcher()` syntax (added parentheses).

#### [MODIFY] [MainActivity.java](file:///C:/Users/Dell/AndroidStudioProjects/ApiSample/app/src/main/java/com/example/apisample/ui/main/MainActivity.java)
- Replace `import android.app.ListActivity;` with `import com.example.apisample.ui.list.ListActivity;` to fix the navigation intent.

## Verification Plan

### Automated Tests
- I will run `gradle_build("app:assembleDebug")` to ensure all compilation errors are resolved.

### Manual Verification
- The user can verify that clicking "Load Users" in `MainActivity` now correctly opens the `ListActivity`.
- The back button on the Toolbar in `ListActivity` should correctly return to `MainActivity`.
