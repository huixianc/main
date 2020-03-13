package seedu.nova.model.common.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.nova.model.addressbook.NovaAddressBook;
import seedu.nova.model.addressbook.ReadOnlyAddressBook;
import seedu.nova.model.common.person.Address;
import seedu.nova.model.common.person.Email;
import seedu.nova.model.common.person.Name;
import seedu.nova.model.common.person.Person;
import seedu.nova.model.common.person.Phone;
import seedu.nova.model.common.person.Remark;
import seedu.nova.model.common.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), EMPTY_REMARK),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), EMPTY_REMARK),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), EMPTY_REMARK),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), EMPTY_REMARK),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), EMPTY_REMARK),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), EMPTY_REMARK)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        NovaAddressBook sampleAb = new NovaAddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}