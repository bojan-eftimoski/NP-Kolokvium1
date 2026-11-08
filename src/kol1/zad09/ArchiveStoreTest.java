package kol1.zad09;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NonExistingItemException extends Exception {

    public NonExistingItemException(String message) {
        super(message);
    }
}

class CannotOpenException extends Exception {

    public CannotOpenException(String message) {
        super(message);
    }
}

abstract class Archive {

    private int id;
    private LocalDate dateArchived;

    public Archive(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateArchived() {
        return dateArchived;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }

    // throws CannotOpenException when the archive is not allowed to be opened on the given date
    public abstract void open(LocalDate date) throws CannotOpenException;
}

class LockedArchive extends Archive {

    private LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public void open(LocalDate date) throws CannotOpenException {
        if (date.isBefore(dateToOpen)) {
            throw new CannotOpenException(String.format("Item %d cannot be opened before %s", getId(), dateToOpen));
        }
    }
}

class SpecialArchive extends Archive {

    private int maxOpen;
    private int timesOpened;

    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        this.timesOpened = 0;
    }

    @Override
    public void open(LocalDate date) throws CannotOpenException {
        if (timesOpened >= maxOpen) {
            throw new CannotOpenException(String.format("Item %d cannot be opened more than %d times", getId(), maxOpen));
        }
        timesOpened++;
    }
}

class ArchiveStore {

    private List<Archive> archives;
    private StringBuilder log;

    public ArchiveStore() {
        archives = new ArrayList<>();
        log = new StringBuilder();
    }

    public void archiveItem(Archive item, LocalDate date) {
        item.setDateArchived(date);
        archives.add(item);
        log.append(String.format("Item %d archived at %s", item.getId(), date)).append("\n");
    }

    public void openItem(int id, LocalDate date) throws NonExistingItemException {
        Archive item = findById(id);
        if (item == null) {
            throw new NonExistingItemException(String.format("Item with id %d doesn't exist", id));
        }
        try {
            item.open(date);
            log.append(String.format("Item %d opened at %s", id, date)).append("\n");
        } catch (CannotOpenException e) {
            log.append(e.getMessage()).append("\n");
        }
    }

    private Archive findById(int id) {
        for (Archive archive : archives) {
            if (archive.getId() == id) {
                return archive;
            }
        }
        return null;
    }

    public String getLog() {
        return log.toString();
    }
}

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}
