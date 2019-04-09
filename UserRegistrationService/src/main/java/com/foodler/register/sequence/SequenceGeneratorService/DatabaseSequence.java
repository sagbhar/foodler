/**
 * 
 */
package com.foodler.register.sequence.SequenceGeneratorService;

import org.springframework.data.annotation.Id;

/**
 * @author sagbhar
 *
 */
public class DatabaseSequence {

	@Id
	private String id;

	private long seq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

}
